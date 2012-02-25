package kinect.kinectcom.impl;

import com.jniwrapper.*;
import com.jniwrapper.win32.*;
import com.jniwrapper.win32.automation.*;
import com.jniwrapper.win32.automation.impl.*;
import com.jniwrapper.win32.automation.types.*;
import com.jniwrapper.win32.com.*;
import com.jniwrapper.win32.com.impl.*;
import com.jniwrapper.win32.com.types.*;
import com.jniwrapper.win32.com.types.GUID;
import kinect.kinectcom.*;

/**
 * Represents COM interface _GestureProcessor.
 *
 *
 */
public class _GestureProcessorImpl extends IDispatchImpl
    implements _GestureProcessor
{
    public static final String INTERFACE_IDENTIFIER = _GestureProcessor.INTERFACE_IDENTIFIER;
    private static final IID _iid = IID.create(INTERFACE_IDENTIFIER);

    public _GestureProcessorImpl()
    {
    }

    protected _GestureProcessorImpl(IUnknownImpl that) throws ComException
    {
        super(that);
    }

    public _GestureProcessorImpl(IUnknown that) throws ComException
    {
        super(that);
    }

    public _GestureProcessorImpl(CLSID clsid, ClsCtx dwClsContext) throws ComException
    {
        super(clsid, dwClsContext);
    }

    public _GestureProcessorImpl(CLSID clsid, IUnknownImpl pUnkOuter, ClsCtx dwClsContext) throws ComException
    {
        super(clsid, pUnkOuter, dwClsContext);
    }

    public IID getIID()
    {
        return _iid;
    }

    public Object clone()
    {
        return new _GestureProcessorImpl(this);
    }
}
