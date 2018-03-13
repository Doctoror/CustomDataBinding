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
package com.doctoror.customdatabinding.demo.mvvm

import android.widget.Toolbar
import com.doctoror.customdatabinding.BR
import com.doctoror.customdatabinding.demo.DemoViewModel
import com.doctoror.customdatabinding.mvvm.ViewModelBinder

class DemoToolbarBinder : ViewModelBinder<Toolbar, DemoViewModel>() {

    override fun bind(target: Toolbar, model: DemoViewModel) {
        if (model.toolbarMoved) {
            if (target.translationY == 0f) {
                target.translationY = target.height.toFloat()
            }
        } else if (target.translationY != 0f) {
            target.translationY = 0f
        }
    }

    private fun handleToolbarMovedStateChange(target: Toolbar, model: DemoViewModel) {
        if (model.toolbarMoved) {
            if (target.translationY == 0f) {
                target.animate().translationYBy(target.height.toFloat())
            }
        } else if (target.translationY != 0f) {
            target.animate().translationY(0f)
        }
    }

    override fun onPropertyChanged(model: DemoViewModel, propertyId: Int) {
        val toolbar = target
        if (propertyId == BR.toolbarMoved && toolbar != null) {
            handleToolbarMovedStateChange(toolbar, model)
        }
    }
}
