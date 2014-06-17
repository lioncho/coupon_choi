// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mospi.moml.framework.pub.object.http;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.util.*;
import javax.net.ssl.*;
import org.mospi.moml.core.framework.*;
import org.mospi.moml.framework.pub.core.MOMLContext;

// Referenced classes of package org.mospi.moml.framework.pub.object.http:
//            Http, HttpPostFileInfo, HttpRequest

public class HttpTransfer
{

    public HttpTransfer(MOMLContext momlcontext)
    {
        d = momlcontext;
    }

    public ij getInputStream(Http.REQMETHOD_TYPE reqmethod_type, HttpRequest httprequest, int i)
    {
        ij ij1;
        HttpURLConnection httpurlconnection;
        ij1 = new ij(httprequest.getRequestId());
        httpurlconnection = null;
        if(httprequest.getUrl() == null || httprequest.getUrl().length() == 0)
            return ij1;
        try
        {
            StringBuffer stringbuffer = new StringBuffer(httprequest.getUrl());
            StringBuffer stringbuffer1 = new StringBuffer();
            int j = 0;
            HashMap hashmap;
            if((hashmap = httprequest.getParameters()) != null && hashmap.size() > 0)
            {
                String s1;
                for(Iterator iterator = hashmap.entrySet().iterator(); iterator.hasNext(); stringbuffer1.append(s1))
                {
                    if(j++ != 0)
                        stringbuffer1.append("&");
                    java.util.Map.Entry entry;
                    String s = (String)(entry = (java.util.Map.Entry)iterator.next()).getKey();
                    if("".equals(s))
                    {
                        stringbuffer1.append((String)entry.getValue());
                        break;
                    }
                    s1 = (new StringBuilder(String.valueOf(s))).append("=").append((String)entry.getValue()).toString();
                }

            }
            if(reqmethod_type == Http.REQMETHOD_TYPE.GET && hashmap != null && hashmap.size() > 0)
            {
                stringbuffer.append("?");
                stringbuffer.append(stringbuffer1);
            }
            if(stringbuffer.toString().startsWith("https:"))
            {
                a();
                ((HttpsURLConnection)(httpurlconnection = (HttpURLConnection)(new URL(stringbuffer.toString())).openConnection())).setHostnameVerifier(e);
            } else
            {
                httpurlconnection = (HttpURLConnection)(new URL(stringbuffer.toString())).openConnection();
            }
            if(i != -1)
                httpurlconnection.setConnectTimeout(i);
            if(reqmethod_type == Http.REQMETHOD_TYPE.POST)
            {
                httpurlconnection.setRequestMethod("post".toUpperCase(Locale.US));
                httpurlconnection.setDoOutput(true);
            }
            if(Http.CONTENT_TYPE_STR[Http.CONTENT_TYPE.XML.ordinal()].equals(httprequest.getContentType()))
                httpurlconnection.setRequestProperty("Content-Type", "text/xml");
            else
            if(httprequest.getContentType() != null && httprequest.getContentType().length() > 0)
                httpurlconnection.setRequestProperty("Content-Type", httprequest.getContentType());
            for(int k = 0; k < httprequest.getHederFields().size(); k++)
            {
                String as[] = (String[])httprequest.getHederFields().get(k);
                httpurlconnection.setRequestProperty(as[0], as[1]);
            }

            org.mospi.moml.core.framework.a.a(d);
            org.mospi.moml.core.framework.a.a(httpurlconnection, httprequest.getUrl(), hashmap);
            httpurlconnection.connect();
            if(reqmethod_type == Http.REQMETHOD_TYPE.POST)
            {
                OutputStreamWriter outputstreamwriter;
                (outputstreamwriter = new OutputStreamWriter(httpurlconnection.getOutputStream())).write(stringbuffer1.toString());
                outputstreamwriter.flush();
                outputstreamwriter.close();
            }
            ij1.a(true);
            ij1.a(httpurlconnection.getResponseCode());
            InputStream inputstream;
            if((inputstream = httpurlconnection.getInputStream()) != null)
                ij1.b(a(inputstream));
            break MISSING_BLOCK_LABEL_669;
        }
        catch(Exception exception)
        {
            StringBuffer stringbuffer2;
            (stringbuffer2 = new StringBuffer(httprequest.getUrl())).append(" (");
            stringbuffer2.append(exception.toString());
            stringbuffer2.append(")");
            d.setError("network", null, stringbuffer2.toString());
            ij1.a(false);
            ij1.a(exception.toString());
        }
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
        break MISSING_BLOCK_LABEL_679;
        Exception exception1;
        exception1;
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
        throw exception1;
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
        return ij1;
    }

    private static void a()
    {
        TrustManager atrustmanager[] = {
            new ii()
        };
        SSLContext sslcontext;
        (sslcontext = SSLContext.getInstance("TLS")).init(null, atrustmanager, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        return;
        printStackTrace();
    }

    private static String a(InputStream inputstream)
    {
        String s;
        BufferedReader bufferedreader;
        StringBuilder stringbuilder;
        s = "";
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_62;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
        String s1;
        while((s1 = bufferedreader.readLine()) != null) 
            stringbuilder.append(s1);
        break MISSING_BLOCK_LABEL_57;
        printStackTrace();
        s = stringbuilder.toString();
        return s;
    }

    public ij uploadFile(HttpRequest httprequest)
    {
        ij ij1;
        HttpURLConnection httpurlconnection;
        ij1 = new ij(httprequest.getRequestId());
        httpurlconnection = null;
        StringBuffer stringbuffer;
        (httpurlconnection = (HttpURLConnection)(new URL(httprequest.getUrl())).openConnection()).setDoInput(true);
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setUseCaches(false);
        org.mospi.moml.core.framework.a.a(d);
        org.mospi.moml.core.framework.a.a(httpurlconnection, httprequest.getUrl(), httprequest.getParameters());
        httpurlconnection.setRequestMethod(Http.REQ_METHOS_TYPE_STR[Http.REQMETHOD_TYPE.POST.ordinal()]);
        httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
        httpurlconnection.setRequestProperty("Content-Type", (new StringBuilder("multipart/form-data; boundary=")).append(c).toString());
        DataOutputStream dataoutputstream;
        a(dataoutputstream = new DataOutputStream(httpurlconnection.getOutputStream()), httprequest.getParameters());
        a(dataoutputstream, httprequest);
        dataoutputstream.writeBytes((new StringBuilder(String.valueOf(b))).append(c).append(b).append(a).toString());
        dataoutputstream.flush();
        dataoutputstream.close();
        stringbuffer = new StringBuffer();
        IOException ioexception;
        try
        {
            DataInputStream datainputstream = new DataInputStream(httpurlconnection.getInputStream());
            String s;
            while((s = datainputstream.readLine()) != null) 
                stringbuffer.append(s);
            datainputstream.close();
            break MISSING_BLOCK_LABEL_297;
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            ioexception.getMessage();
        }
        ij1.a(true);
        ij1.b(stringbuffer.toString());
        ij1.a(httpurlconnection.getResponseCode());
        break MISSING_BLOCK_LABEL_332;
        Exception exception;
        exception;
        ij1.a(true);
        ij1.b(stringbuffer.toString());
        ij1.a(httpurlconnection.getResponseCode());
        throw exception;
        ij1.a(true);
        ij1.b(stringbuffer.toString());
        ij1.a(httpurlconnection.getResponseCode());
        break MISSING_BLOCK_LABEL_332;
        MalformedURLException malformedurlexception;
        malformedurlexception;
        malformedurlexception.getMessage();
        break MISSING_BLOCK_LABEL_332;
        printStackTrace();
        if(httpurlconnection != null)
            httpurlconnection.disconnect();
        return ij1;
    }

    private static void a(DataOutputStream dataoutputstream, HashMap hashmap)
    {
        Iterator iterator = hashmap.entrySet().iterator();
        while(iterator.hasNext()) 
        {
            dataoutputstream.writeBytes((new StringBuilder(String.valueOf(b))).append(c).append(a).toString());
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            dataoutputstream.writeBytes((new StringBuilder("Content-Disposition: form-data; name=\"")).append((String)entry.getKey()).append("\"").append(a).toString());
            dataoutputstream.writeBytes((new StringBuilder("Content-Type: text/plain; charset=UTF-8")).append(a).append(a).toString());
            dataoutputstream.write(((String)entry.getValue()).getBytes("utf-8"));
            dataoutputstream.writeBytes(a);
        }
        return;
        printStackTrace();
    }

    private static void a(DataOutputStream dataoutputstream, HttpRequest httprequest)
    {
        if(httprequest != null)
        {
            for(Iterator iterator = httprequest.getPostFileInfos().iterator(); iterator.hasNext(); dataoutputstream.writeBytes(a))
            {
                dataoutputstream.writeBytes((new StringBuilder(String.valueOf(b))).append(c).append(a).toString());
                HttpPostFileInfo httppostfileinfo = (HttpPostFileInfo)iterator.next();
                dataoutputstream.writeBytes((new StringBuilder("Content-Disposition: form-data; name=\"")).append(httppostfileinfo.getFileKey()).append("\"; filename=\"").append(httppostfileinfo.getFileName()).append("\"").append(a).toString());
                dataoutputstream.writeBytes((new StringBuilder("Content-Type: ")).append(httppostfileinfo.getContentType()).append(a).append(a).toString());
                byte abyte0[];
                if((abyte0 = httppostfileinfo.getFileContents()) != null)
                    dataoutputstream.write(abyte0);
            }

            return;
        }
        break MISSING_BLOCK_LABEL_176;
        printStackTrace();
    }

    private static String a = "\r\n";
    private static String b = "--";
    private static String c = "aB03x";
    private MOMLContext d;
    private static final HostnameVerifier e = new ih();

}
