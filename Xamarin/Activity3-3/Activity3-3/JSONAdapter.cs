using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Org.Json;
using Android.Util;

namespace Activity3_3
{
    class JSONAdapter : BaseAdapter
    {

        Activity activity;
        JSONArray data;

        public JSONAdapter(Activity activity, JSONArray data)
        {
            this.activity = activity;
            this.data = data;
        }

        public override int Count
        {
            get
            {
                int length = 0;
                try
                {
                    length = data.Length();
                }
                catch(Exception e)
                {
                    Log.Debug("EXCEPTION", e.Message);
                }
                return length;
            }
        }

        public override Java.Lang.Object GetItem(int position)
        {
            JSONObject toReturn = null;
            try
            {
                toReturn = data.GetJSONObject(position);
            }
            catch(Exception e)
            {
                Log.Debug("EXCEPTION", e.Message);
            }
            return toReturn;
        }

        public override long GetItemId(int position)
        {
            return position;
        }

        public override View GetView(int position, View convertView, ViewGroup parent)
        {
            if(convertView == null)
            {
                convertView = activity.LayoutInflater.Inflate(Resource.Layout.layout1, null);
            }

            string title = "";
            string body = "";

            try
            {
                title = data.GetJSONObject(position).GetString("title");
                body = data.GetJSONObject(position).GetString("body");
            }
            catch(Exception e)
            {
                Log.Debug("EXCEPTION", e.Message);
            }

            TextView large = convertView.FindViewById<TextView>(Resource.Id.textView1);
            TextView small = convertView.FindViewById<TextView>(Resource.Id.textView2);

            large.Text = title;
            small.Text = body;

            return convertView;
        }
    }
}