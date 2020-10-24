package com.samir.foodvloggers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import com.parse.ParseQuery
import com.samir.foodvloggers.Trending.TrendingListAdapter
import com.samir.foodvloggers.databinding.TrendingsFragmentBinding


class Trendings : Fragment() {

    companion object {
        fun newInstance() = Trendings()
    }

    private lateinit var viewModel: TrendingsViewModel
    private lateinit var binding: TrendingsFragmentBinding

    private val USER_ID: String = "user_ID"
    private val RESTAURANT_NAME = "restaurant_NAME"
    private val RESTAURANT_LOCATION = "restaurant_LOCATION"
    private val VIDEO_URL = "video_URL"
    private val VIDEO_ID = "video_ID"
    private val USER_NAME = "user_NAME"

    private lateinit var name_restaurant: EditText
    private lateinit var location_restaurant: EditText
    private lateinit var video_URL: EditText
    private lateinit var video_ID: String

    //views
    private lateinit var recyclerView: RecyclerView
    private lateinit var queryResults: ArrayList<ParseObject>
    private lateinit var adapter: TrendingListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.trendings_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TrendingsViewModel::class.java)

        queryResults = ArrayList<ParseObject>()

        adapter = TrendingListAdapter(queryResults)

        //queryResults.add(ParseObject("Videos"))

        //vlogs recyclerView setup
        val linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter

        //ends

        //retrieving vlogs data from parse server
        retrieveDataFromServer()
    }

    private fun retrieveDataFromServer() {

        val queryAllVlogs = ParseQuery.getQuery<ParseObject>("Videos")
        queryAllVlogs.whereExists("restaurant_NAME")
        queryAllVlogs.findInBackground { results, e ->
            // results has the list of users with a hometown team with a winning record
            if (e == null) {
                Log.i("Parse", "Data successfully retrieved")
                Log.i("Parse", queryAllVlogs.toString())
                for (vlog in results) {
                    val temp = vlog.getString("restaurant_NAME")
                    val temp2 = vlog.getString("restaurant_LOCATION")
                    queryResults.add(vlog)

                    adapter.notifyDataSetChanged()
                    //Log.i("Parse", temp +"\n" + temp2)

                }
            } else {
                Log.e("Parse Error", e.printStackTrace().toString())
                Log.e("Parse Error Message :", e.message.toString())
            }
        }
    }


    override fun onStart() {
        super.onStart()

        //Input Dialog for adding a new vlog
        val mView = LayoutInflater.from(context).inflate(R.layout.add_new_vlog_dialog, null)

        name_restaurant = mView.findViewById(R.id.restaurant_name)
        location_restaurant = mView.findViewById(R.id.restaurant_location)
        video_URL = mView.findViewById(R.id.video_URL)

        val cancelButton: Button = mView.findViewById(R.id.cancelButton)
        val okButton: Button = mView.findViewById(R.id.okButton)

        val alert = AlertDialog.Builder(requireContext())
        alert.setView(mView)
         val alertDialog = alert.create()
        alertDialog.setCanceledOnTouchOutside(false)

        binding.addNewVlogButton.setOnClickListener {
            alertDialog.show()
        }

        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        okButton.setOnClickListener {

            Log.i("223", "Working ok ***")
            
            //extracting video URL
            val temp = video_URL.text.toString().split("v=")
            video_ID = temp[1]

            Log.i("Parse", video_ID)

            alertDialog.dismiss()

            putData()

        }

    }



    private fun putData() {

        val insertVlog = ParseObject("Videos")
        insertVlog.put(USER_ID, "1337")
        insertVlog.put(RESTAURANT_NAME, name_restaurant.text.toString())
        insertVlog.put(RESTAURANT_LOCATION, location_restaurant.text.toString())
        insertVlog.put(VIDEO_URL, video_URL.text.toString())
        insertVlog.put(VIDEO_ID, video_ID)
        insertVlog.put(USER_NAME, "Dummy Data")
        insertVlog.saveInBackground {
            if (it == null) {
                Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
            }else{
                Log.i("Parse", it.message.toString())
                Log.i("Parse", it.stackTrace.toString())
            }
        }

        Toast.makeText(context,"Info Saved",Toast.LENGTH_SHORT).show()

    }



}