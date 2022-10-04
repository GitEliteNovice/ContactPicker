package com.aryan.fastcontacts_picker.picker

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.aryan.fastcontacts_picker.ContactInfo

class ContactLogicImpl(private val context: Context) : ContactLogic {

    private val contactList = ArrayList<ContactInfo>()

    @SuppressLint("Range")
    override fun contacts(): ArrayList<ContactInfo> {
        contactList.clear()
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val selection = ContactsContract.Contacts.HAS_PHONE_NUMBER
        var cursor = context.contentResolver.query(
            uri,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.Contacts._ID
            ),
            selection,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        cursor?.let {

            it.moveToFirst()
            while (!it.isAfterLast) {

                val contactNumber =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val contactName =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneContactID =
                    it.getInt(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
                val contactID = it.getInt(it.getColumnIndex(ContactsContract.Contacts._ID))
                //  Log.d("con ", "name " + contactName + " " + " PhoeContactID " + phoneContactID + "  ContactID " + contactID);
                contactList.add(
                    ContactInfo(
                        contactName,
                        (contactNumber.replace("\\s+".toRegex(), "")
                            .replace(("[-" + "^]*").toRegex(), ""))
                    )
                )
                it.moveToNext()
            }
            it.close()
        }

        Log.d("value::", "=-" + contactList.size + "----");
        return contactList;
    }
}