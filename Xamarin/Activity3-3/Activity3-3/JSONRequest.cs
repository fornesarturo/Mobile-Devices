using System;
using Android.OS;
using Android.Runtime;
using Android.Util;
using System.Net.Http;
using Newtonsoft.Json;
using System.Collections.Generic;
using Java.Lang;
using Org.Json;

namespace Activity3_3
{
    public class JSONRequest : AsyncTask<string, int, JSONArray >
    {
        private JSONListener listener;

        
        public JSONRequest(JSONListener listener) {
            this.listener = listener;
        }

        protected override JSONArray RunInBackground(params string[] @params) {
            //JavaList<Post> result = null;
            JSONArray resulting = new JSONArray();
            try
            {
                HttpClient client = new HttpClient();
                HttpResponseMessage response = client.GetAsync(@params[0]).Result;
                
                if (response.IsSuccessStatusCode)
                {
                    string responseString = response.Content.ReadAsStringAsync().Result;
                    //result = JsonConvert.DeserializeObject<JavaList<Post>>(responseString);
                    resulting = new JSONArray(responseString);
                }

                Log.Debug("PRIMERO", resulting.GetJSONObject(0).ToString());
            }
            catch (AggregateException ae) {
                Log.Debug("EXCEPTION", ae.Message);
            }
            return resulting;
        }

        protected override void OnPostExecute(Java.Lang.Object result)
        {
            listener.Listen(result as JSONArray);
        }

        public interface JSONListener
        {
            void Listen(JSONArray posts);
        }
    }
}