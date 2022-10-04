package com.aryan.fastcontacts_picker.picker

import com.aryan.fastcontacts_picker.ContactInfo

interface ContactLogic {
    fun contacts(): ArrayList<ContactInfo>
}