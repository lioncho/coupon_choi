// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mospi.moml.framework.pub.object.http;

import java.io.PrintStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mospi.moml.framework.pub.core.MOMLContext;

// Referenced classes of package org.mospi.moml.framework.pub.object.http:
//            HttpPostFileInfo

public class HttpRequest
{

    public HttpRequest(int i)
    {
        d = new ArrayList();
        a = i;
    }

    public void addPostFileInfo(MOMLContext momlcontext, String s, String s1)
    {
        HttpPostFileInfo httppostfileinfo;
        if((httppostfileinfo = a(s)) != null)
        {
            httppostfileinfo.setFilePath(s1);
            return;
        } else
        {
            d.add(new HttpPostFileInfo(momlcontext, s, s1));
            return;
        }
    }

    private HttpPostFileInfo a(String s)
    {
        HttpPostFileInfo httppostfileinfo = null;
        HttpPostFileInfo httppostfileinfo1;
        for(Iterator iterator = d.iterator(); iterator.hasNext();)
            if((httppostfileinfo1 = (HttpPostFileInfo)iterator.next()).getFileKey().equals(s))
            {
                httppostfileinfo = httppostfileinfo1;
                break;
            }

        return httppostfileinfo;
    }

    public void removePostFileInfo(String s)
    {
        HttpPostFileInfo httppostfileinfo;
        if((httppostfileinfo = a(s)) != null)
            d.remove(httppostfileinfo);
    }

    public List getPostFileInfos()
    {
        return Collections.unmodifiableList(d);
    }

    public int getPostFileInfoSize()
    {
        return d.size();
    }

    public int getRequestId()
    {
        return a;
    }

    public void setUrl(String s)
    {
        b = s;
    }

    public String getUrl()
    {
        return b;
    }

    public void setParameters(String s)
    {
        c = b(s);
    }

    public HashMap getParameters()
    {
        return c;
    }

    private static HashMap b(String s)
    {
        HashMap hashmap = new HashMap();
        if(s == null)
            return hashmap;
        if(s.length() <= 0)
            return hashmap;
        Matcher matcher = Pattern.compile("[^\\\\]\\|").matcher(s);
        System.out.println(s);
        int i = 0;
        boolean flag;
        for(flag = false; matcher.find(); flag = true)
        {
            String s1;
            int j;
            if((j = (s1 = s.substring(i, matcher.start() + 1).replace("\\|", "|")).indexOf("=")) != -1)
                hashmap.put(s1.substring(0, j), s1.substring(j + 1));
            i = matcher.end();
        }

        String s2;
        int k;
        if(flag)
        {
            if((k = (s2 = s.substring(i).replace("\\|", "|")).indexOf("=")) != -1)
                hashmap.put(s2.substring(0, k), s2.substring(k + 1));
        } else
        if(s.length() != 0)
        {
            String s3 = s.replace("\\|", "|");
            hashmap.put("", s3);
        }
        return hashmap;
    }

    public void setContentType(String s)
    {
        e = s;
    }

    public String getContentType()
    {
        return e;
    }

    public void clearPostFileInfos()
    {
        d.clear();
    }

    private int a;
    private String b;
    private HashMap c;
    private ArrayList d;
    private String e;
}
