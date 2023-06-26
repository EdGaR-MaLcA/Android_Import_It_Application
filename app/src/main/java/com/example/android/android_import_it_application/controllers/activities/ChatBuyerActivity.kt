package com.example.android.android_import_it_application.controllers.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.MessageAdapter
import com.example.android.android_import_it_application.controllers.fragments.EnterCouponFragment
import com.example.android.android_import_it_application.controllers.fragments.SavedCouponsFragment
import com.example.android.android_import_it_application.database.CouponDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
class ChatBuyerActivity: AppCompatActivity() {
    private lateinit var listViewMessages: ListView
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button
    private val messagesList: ArrayList<String> = ArrayList()
    private lateinit var messagesAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer_chats)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Soporte"



        listViewMessages = findViewById(R.id.listViewMessages)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSend = findViewById(R.id.buttonSend)

        messagesAdapter = MessageAdapter(this, messagesList)
        listViewMessages.adapter = messagesAdapter

        buttonSend.setOnClickListener {
            val message = editTextMessage.text.toString()
            sendMessage(message)
            editTextMessage.text.clear()
        }
    }
    private fun sendMessage(message: String) {
        messagesList.add(message)
        messagesAdapter.notifyDataSetChanged()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent: Intent = Intent(this, BuyerActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}