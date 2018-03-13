/*
 * Copyright (C) 2018 Yaroslav Mytkalyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.doctoror.customdatabinding.mvvm

import android.databinding.BaseObservable
import android.databinding.Observable

/**
 * Used for binding View [Model]s to [Target] objects.
 */
abstract class ViewModelBinder<Target, Model : BaseObservable> {

    var target: Target? = null
        set(value) {
            field = value
            attemptBind(value, model)
        }

    var model: Model? = null
        set(value) {
            val oldValue = field
            if (oldValue != null && oldValue != value) {
                onModelCleared(oldValue)
            }
            field = value
            if (value != null) {
                onModelSet(value)
            }
            attemptBind(target, value)
        }

    private fun onModelCleared(oldModel: Model) {
        oldModel.removeOnPropertyChangedCallback(modelChangeCallback)
    }

    private fun onModelSet(newModel: Model) {
        newModel.addOnPropertyChangedCallback(modelChangeCallback)
    }

    private fun attemptBind(target: Target?, model: Model?) {
        if (target != null && model != null) {
            bind(target, model)
        }
    }

    /**
     * Bind the [model] to the [target]. The initial state of the [model]-[target] pair should be set here.
     * This will be invoked every time the [model] and the [target] pair is amended by the setters (and when
     * both are set to non-null).
     */
    protected abstract fun bind(target: Target, model: Model)

    /**
     * This method will be called every time a property with a [propertyId] in the [model] changes.
     * This is the place where the [target] should be updated based on the [model] property change.
     */
    protected abstract fun onPropertyChanged(model: Model, propertyId: Int)

    private val modelChangeCallback =
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(o: Observable, propertyId: Int) {
                    @Suppress("UNCHECKED_CAST")
                    this@ViewModelBinder.onPropertyChanged(o as Model, propertyId)
                }
            }
}
