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
 * Represents COM interface _OnTrackingStartedDel.
 *
 *
 */
public class _OnTrackingStartedDelImpl extends IDispatchImpl
    implements _OnTrackingStartedDel
{
    public static final String INTERFACE_IDENTIFIER = _OnTrackingStartedDel.INTERFACE_IDENTIFIER;
    private static final IID _iid = IID.create(INTERFACE_IDENTIFIER);

    public _OnTrackingStartedDelImpl()
    {
    }

    protected _OnTrackingStartedDelImpl(IUnknownImpl that) throws ComException
    {
        super(that);
    }

    public _OnTrackingStartedDelImpl(IUnknown that) throws ComException
    {
        super(that);
    }

    public _OnTrackingStartedDelImpl(CLSID clsid, ClsCtx dwClsContext) throws ComException
    {
        super(clsid, dwClsContext);
    }

    public _OnTrackingStartedDelImpl(CLSID clsid, IUnknownImpl pUnkOuter, ClsCtx dwClsContext) throws ComException
    {
        super(clsid, pUnkOuter, dwClsContext);
    }

    public IID getIID()
    {
        return _iid;
    }

    public Object clone()
    {
        return new _OnTrackingStartedDelImpl(this);
    }
}
