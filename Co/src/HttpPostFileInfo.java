// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mospi.moml.framework.pub.object.http;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLConnection;
import org.apache.http.util.ByteArrayBuffer;
import org.mospi.moml.framework.pub.core.MOMLContext;
import org.mospi.moml.framework.pub.core.ResourceManager;

public class HttpPostFileInfo
{

    public HttpPostFileInfo(MOMLContext momlcontext, String s, String s1)
    {
        if(s == null || s.length() == 0)
            return;
        if(s1 == null || s1.length() == 0)
        {
            return;
        } else
        {
            a = momlcontext;
            b = s;
            c = s1;
            return;
        }
    }

    public String getFileKey()
    {
        return b;
    }

    public String getFilePath()
    {
        return c;
    }

    public String getFileName()
    {
        String s;
        int i;
        if((i = (s = c).indexOf(":")) != -1)
            s = s.substring(i + 1);
        if((i = s.lastIndexOf("/")) != -1)
            s = s.substring(i + 1);
        return s;
    }

    public String getContentType()
    {
        return URLConnection.guessContentTypeFromName(a.getResFileManager().convertStoragePathToAbolutePath(c));
    }

    public byte[] getFileContents()
    {
        ByteArrayBuffer bytearraybuffer;
        File file;
        FileInputStream fileinputstream;
        bytearraybuffer = new ByteArrayBuffer(0);
        String s;
        if((s = a.getResFileManager().convertStoragePathToAbolutePath(getFilePath())) == null)
            return bytearraybuffer.toByteArray();
        file = new File(s);
        fileinputstream = null;
        int j;
        byte abyte0[] = new byte[j = Math.min((fileinputstream = new FileInputStream(file)).available(), 0x100000)];
        for(int i = fileinputstream.read(abyte0, 0, j); i > 0; i = fileinputstream.read(abyte0, 0, j))
        {
            bytearraybuffer.append(abyte0, 0, i);
            j = Math.min(fileinputstream.available(), 0x100000);
        }

        break MISSING_BLOCK_LABEL_166;
        printStackTrace();
        if(fileinputstream != null)
            fileinputstream.close();
        break MISSING_BLOCK_LABEL_177;
        printStackTrace();
        break MISSING_BLOCK_LABEL_177;
        Exception exception;
        exception;
        if(fileinputstream != null)
            fileinputstream.close();
        break MISSING_BLOCK_LABEL_163;
        printStackTrace();
        throw exception;
        fileinputstream.close();
        break MISSING_BLOCK_LABEL_177;
        printStackTrace();
        return bytearraybuffer.toByteArray();
    }

    public void setFilePath(String s)
    {
        c = s;
    }

    private MOMLContext a;
    private String b;
    private String c;
}
