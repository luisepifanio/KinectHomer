package kinect.mscorlib;

import com.jniwrapper.*;
import com.jniwrapper.win32.*;
import com.jniwrapper.win32.com.*;
import com.jniwrapper.win32.com.impl.*;
import com.jniwrapper.win32.com.types.*;
import com.jniwrapper.win32.com.types.GUID;
import kinect.mscorlib.impl.*;

/**
 * Represents Java interface for COM interface _Module.
 *
 *
 */
public interface _Module extends IUnknown
{
    public static final String INTERFACE_IDENTIFIER = "{D002E9BA-D9E3-3749-B1D3-D565A08B13E7}";

    /**
     * 
     */
    void getTypeInfoCount(
        UInt32 /*[out]*/ pcTInfo)
        throws ComException;

    /**
     * 
     */
    void getTypeInfo(
        UInt32 /*[in]*/ iTInfo,
        UInt32 /*[in]*/ lcid,
        Int32 /*[in]*/ ppTInfo)
        throws ComException;

    /**
     * 
     */
    void getIDsOfNames(
        GUID /*[in]*/ riid,
        Int32 /*[in]*/ rgszNames,
        UInt32 /*[in]*/ cNames,
        UInt32 /*[in]*/ lcid,
        Int32 /*[in]*/ rgDispId)
        throws ComException;

    /**
     * 
     */
    void invoke(
        UInt32 /*[in]*/ dispIdMember,
        GUID /*[in]*/ riid,
        UInt32 /*[in]*/ lcid,
        Int16 /*[in]*/ wFlags,
        Int32 /*[in]*/ pDispParams,
        Int32 /*[in]*/ pVarResult,
        Int32 /*[in]*/ pExcepInfo,
        Int32 /*[in]*/ puArgErr)
        throws ComException;
}
