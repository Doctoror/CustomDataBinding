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

import android.view.Menu
import android.view.MenuItem
import com.doctoror.customdatabinding.BR
import com.doctoror.customdatabinding.R
import com.doctoror.customdatabinding.demo.DemoViewModel
import com.doctoror.customdatabinding.mvvm.ViewModelBinder

class DemoMenuBinder : ViewModelBinder<Menu, DemoViewModel>() {

    private var item: MenuItem? = null

    override fun bind(target: Menu, model: DemoViewModel) {
        item = target.findItem(R.id.actionMoveToolbar)
        updateMoveToolbarItem(model)
    }

    private fun updateMoveToolbarItem(model: DemoViewModel) {
        item?.isEnabled = model.moveToolbarOptionEnabled
    }

    override fun onPropertyChanged(model: DemoViewModel, propertyId: Int) {
        if (propertyId == BR.moveToolbarOptionEnabled) {
            updateMoveToolbarItem(model)
        }
    }

}
