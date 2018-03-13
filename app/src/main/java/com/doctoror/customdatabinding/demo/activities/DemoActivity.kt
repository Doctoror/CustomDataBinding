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
package com.doctoror.customdatabinding.demo.activities

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.doctoror.customdatabinding.R
import com.doctoror.customdatabinding.databinding.ActivityDemoBinding
import com.doctoror.customdatabinding.demo.mvvm.DemoMenuBinder
import com.doctoror.customdatabinding.demo.presenters.DemoPresenter
import com.doctoror.customdatabinding.demo.mvvm.DemoToolbarBinder
import com.doctoror.customdatabinding.demo.DemoViewModel

class DemoActivity : Activity() {

    private val menuBinder = DemoMenuBinder()
    private val toolbarBinder = DemoToolbarBinder()

    private val model = DemoViewModel()
    private val presenter = DemoPresenter(model)

    init {
        toolbarBinder.model = model
        menuBinder.model = model
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
    }

    private fun initContentView() {
        val binding = DataBindingUtil.setContentView<ActivityDemoBinding>(
                this, R.layout.activity_demo)
        binding.model = model
        toolbarBinder.target = binding.toolbar

        setActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.demo, menu)
        menuBinder.target = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actionMoveToolbar -> {
            presenter.onActionMoveToolbar()
            true
        }
        R.id.actionEnableToolbarColor -> {
            presenter.onActionEnableDisableChangeToolbarColor()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
