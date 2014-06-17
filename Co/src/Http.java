// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mospi.moml.framework.pub.object.http;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.mospi.moml.core.framework.*;
import org.mospi.moml.framework.pub.core.MOMLContext;
import org.mospi.moml.framework.pub.object.MOMLApplication;
import org.mospi.moml.framework.pub.objectapi.ObjectApiInfo;

// Referenced classes of package org.mospi.moml.framework.pub.object.http:
//            HttpRequest, HttpTransfer

public class Http extends ed
{

    public String getName()
    {
        return "http";
    }

    public static ObjectApiInfo getObjectApiInfo()
    {
        if(objApiInfo == null)
        {
            (objApiInfo = ObjectApiInfo.createObjectApiInfo("http", "1.1.0", "1.0.7", "", org/mospi/moml/framework/pub/object/http/Http.getSuperclass())).registerMethod("clearPostFileInfo", null, 1, "1.1.0", "1.0.7", "");
            objApiInfo.registerMethod("addPostFileInfo", null, 3, "1.1.0", "1.0.7", "");
            objApiInfo.registerMethod("removePostFileInfo", null, 2, "1.1.0", "1.0.7", "");
            objApiInfo.registerMethod("post", null, 3, "1.1.0", "1.0.7", "");
            objApiInfo.registerMethod("get", null, 3, "1.1.0", "1.0.7", "");
            objApiInfo.registerProperty("timeout", null, "1.1.0", "1.0.7", "");
            objApiInfo.registerMethod("setContentType", null, 2, "1.1.0", "1.0.7", "");
            objApiInfo.registerMethod("createRequestId", "getNextRequestId", 0, "1.1.0", "1.0.7", "");
        }
        return objApiInfo;
    }

    public Http(MOMLContext momlcontext)
    {
        super(momlcontext);
        b = new ConcurrentHashMap();
        d = -1;
        e = new hx(this);
        f = new HttpTransfer(momlcontext);
    }

    public void clearPostFileInfo(int i)
    {
        HttpRequest httprequest;
        if((httprequest = (HttpRequest)b.get(Integer.valueOf(i))) != null)
            httprequest.clearPostFileInfos();
    }

    private HttpRequest a(int i)
    {
        HttpRequest httprequest;
        if((httprequest = (HttpRequest)b.get(Integer.valueOf(i))) == null)
        {
            httprequest = new HttpRequest(i);
            b.put(Integer.valueOf(i), httprequest);
        }
        return httprequest;
    }

    public void addPostFileInfo(int i, String s, String s1)
    {
        a(i).addPostFileInfo(getMomlContext(), s, s1);
    }

    public void removePostFileInfo(int i, String s)
    {
        HttpRequest httprequest;
        if((httprequest = (HttpRequest)b.get(Integer.valueOf(i))) != null)
            httprequest.removePostFileInfo(s);
    }

    public int get(int i, String s, String s1)
    {
        if(i == -1)
            i = getNextRequestId();
        (new Thread((new hy(this)).a(i, s, s1))).start();
        return i;
    }

    public int post(int i, String s, String s1)
    {
        if(i == -1)
            i = getNextRequestId();
        (new Thread((new hz(this)).a(i, s, s1))).start();
        return i;
    }

    public synchronized int getNextRequestId()
    {
        return ++a;
    }

    private void b(int i)
    {
        clearPostFileInfo(i);
        setContentType(i, null);
        b.remove(Integer.valueOf(i));
    }

    private void a(id id1)
    {
        b(id1.d());
        Message message;
        (message = new Message()).what = 0;
        ArrayList arraylist;
        (arraylist = new ArrayList()).add(c[ia.a.ordinal()]);
        arraylist.add(Integer.toString(id1.d()));
        arraylist.add(c[ia.b.ordinal()]);
        arraylist.add(Integer.toString(id1.e()));
        arraylist.add(c[ia.c.ordinal()]);
        arraylist.add(Integer.toString(id1.a()));
        arraylist.add(c[ia.d.ordinal()]);
        arraylist.add(id1.c());
        arraylist.add(c[ia.e.ordinal()]);
        arraylist.add(id1.b());
        message.obj = arraylist;
        e.sendMessage(message);
    }

    public int getTimeout()
    {
        int i = -1;
        if(d != -1)
            i = d;
        if(i == -1)
            i = getMomlContext().getApplicationInfo().getNetworkTimeout();
        return i;
    }

    public void setTimeout(int i)
    {
        d = i;
    }

    public void setContentType(int i, String s)
    {
        a(i).setContentType(s);
    }

    public static int a()
    {
        return 0;
    }

    public static HttpRequest a(Http http, int i)
    {
        return http.a(i);
    }

    public static HttpTransfer a(Http http)
    {
        return http.f;
    }

    public static int b(Http http)
    {
        return http.d;
    }

    public static void a(Http http, id id1)
    {
        http.a(id1);
    }

    private static int a = 0;
    private ConcurrentHashMap b;
    public static String REQ_METHOS_TYPE_STR[] = {
        "GET", "POST"
    };
    private String c[] = {
        "requestId", "requestResult", "responseCode", "responseValue", "errorMessage"
    };
    private int d;
    public static String CONTENT_TYPE_STR[] = {
        "text", "xml"
    };
    private Handler e;
    private HttpTransfer f;
    public static ObjectApiInfo objApiInfo;

}
