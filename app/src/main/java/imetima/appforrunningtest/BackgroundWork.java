package imetima.appforrunningtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWork extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;


    public BackgroundWork(Context context) {
        this.context = context;
    }

    private String doJob(String service_url,String... params){
        try {
            String uname = params[1];
            String pw = params[2];
            URL url = new URL(service_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8")+"&"+
                    URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pw,"UTF-8") ;
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result = "";
            String line;
            while( (line = bufferedReader.readLine()) != null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return  result;
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "Failed";
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String service_url = "http://10.0.2.2/";
        if(type.equals("login")){
            service_url = service_url + "login.php";
            if(doJob(service_url,params).equals("SuccessL")){
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
            }
            else if(doJob(service_url,params).equals("FailedL")){
                Intent intent = new Intent(context,RegisterActivity.class);
                context.startActivity(intent);
            }
            else {
                Toast.makeText(context, "Failed to connect to server !", Toast.LENGTH_SHORT).show();
            }
        }
        else if(type.equals("register")){
            service_url = service_url + "register.php";
            if(doJob(service_url,params).equals("SuccessR")){
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
            }
            else if(doJob(service_url,params).equals("FailedR")){
                Toast.makeText(context, "Username is already taken, try another one.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Failed to connect to server !", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
       alertDialog = new AlertDialog.Builder(context).create();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
