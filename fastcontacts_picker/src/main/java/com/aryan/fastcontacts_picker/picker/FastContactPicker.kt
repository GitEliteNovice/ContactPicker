package com.aryan.fastcontacts_picker.picker

import com.aryan.fastcontacts_picker.ContactInfo

abstract class FastContactPicker {
  abstract suspend fun  getContacts(): ArrayList<ContactInfo>?
}