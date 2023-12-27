package com.example.contacts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.contacts.databinding.ActivityAddContactBinding

class AddContact : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getStringExtra("type")) {
            "update" -> {
                title = "Update Contact"
                binding.etName.setText(intent.getStringExtra("name"))
                binding.etNumber.setText(intent.getStringExtra("number"))
                val id: Int = intent.getIntExtra("id", 0)
                binding.add.text = "Update"
                binding.add.setOnClickListener {
                    val replyIntent = Intent()
                    if (TextUtils.isEmpty(binding.etName.text)) {
                        setResult(0, replyIntent)
                    } else {
                        val title = binding.etName.text.toString()
                        val desc = binding.etNumber.text.toString()
                        replyIntent.putExtra(EXTRA_REPLY, title)
                        replyIntent.putExtra(EXTRA_REPLY2, desc)
                        replyIntent.putExtra("id", id)
                        setResult(2, replyIntent)
                    }
                    finish()
                }
            }

            else -> {
                title = "Add Contact"
                binding.add.setOnClickListener {
                    val replyIntent = Intent()
                    if (TextUtils.isEmpty(binding.etName.text)) {
                        setResult(0, replyIntent)
                    } else {
                        val name = binding.etName.text.toString()
                        val number = binding.etNumber.text.toString()
                        replyIntent.putExtra(EXTRA_REPLY, name)
                        replyIntent.putExtra(EXTRA_REPLY2, number)
                        setResult(Activity.RESULT_OK, replyIntent)
                    }
                    finish()
                }
            }
        }

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.contact.REPLY"
        const val EXTRA_REPLY2 = "com.example.android.contact.REPLY2"
    }

}