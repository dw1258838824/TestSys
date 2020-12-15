// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   RequestUtils.java

package com.ruoyi.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils
{

    public RequestUtils()
    {
    }

    public static Map getRequestMap(HttpServletRequest request)
    {
        Map parm = new HashMap();
        try
        {
            Map parms = request.getParameterMap();
            Set entrySet = parms.entrySet();
            java.util.Map.Entry e;
            for(Iterator iterator = entrySet.iterator(); iterator.hasNext(); parm.put((String)e.getKey(), ((String[])e.getValue())[0]))
                e = (java.util.Map.Entry)iterator.next();

        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return parm;
    }

    public static Hashtable getRequestTable(HttpServletRequest request)
    {
        Hashtable parm = new Hashtable();
        try
        {
            Map parms = request.getParameterMap();
            Set entrySet = parms.entrySet();
            java.util.Map.Entry e;
            for(Iterator iterator = entrySet.iterator(); iterator.hasNext(); parm.put((String)e.getKey(), ((String[])e.getValue())[0]))
                e = (java.util.Map.Entry)iterator.next();

        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return parm;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
        throws IOException
    {
        int contentLength = request.getContentLength();
        if(contentLength < 0)
            return null;
        byte buffer[] = new byte[contentLength];
        int readlen;
        for(int i = 0; i < contentLength; i += readlen)
        {
            readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if(readlen == -1)
                break;
        }

        return buffer;
    }

    public static String getRequestPostStr(HttpServletRequest request)
        throws IOException
    {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if(charEncoding == null)
            charEncoding = "UTF-8";
        return new String(buffer, charEncoding);
    }
}
