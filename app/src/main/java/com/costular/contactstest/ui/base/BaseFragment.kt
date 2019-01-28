package com.costular.contactstest.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

}