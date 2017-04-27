using Android.App;
using Android.Widget;
using Android.OS;
using System;
using System.Collections.Generic;
using Android.Runtime;
using Org.Json;

namespace Activity3_3
{
    [Activity(Label = "Activity3_3", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : Activity , JSONRequest.JSONListener
    {
        Button buttonJSON;
        ListView lv;

        public void Listen(JSONArray posts)
        {
            JSONAdapter adapter = new JSONAdapter(this, posts);
            lv.Adapter = adapter;
        }

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView (Resource.Layout.Main);

            buttonJSON = FindViewById<Button>(Resource.Id.button_get_json);
            lv = FindViewById<ListView>(Resource.Id.listView_json);

            buttonJSON.Click += delegate
            {
                JSONRequest request = new JSONRequest(this);
                request.Execute("http://jsonplaceholder.typicode.com/posts");
            };
        }


    }
}

