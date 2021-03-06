package uk.ac.stir.cs.homer.homerPolicyServer.policies.terms.whenTerms;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.stir.cs.homer.homerPolicyServer.LivePolicy;
import uk.ac.stir.cs.homer.homerPolicyServer.policies.terms.TermType;

public abstract class WhenTerm implements TermListener
{	
	private LivePolicy livepolicy;
	protected TermType termType;
	protected List<WhenTerm> children;
	protected TermListener parentListener;
	
	public static WhenTerm create(LivePolicy livepolicy, JSONObject json, TermListener parentListener, boolean startListening) throws JSONException
	{
		WhenTerm t = null;
		JSONArray jsonToPassToChild = null;
		
		int duration = 0;
		if (json.has("within"))
			duration = Integer.parseInt(json.getString("within"));
		
		
		if (json.has(TermType.AND.name()))
		{
			t = new WhenAndTerm(duration);
			jsonToPassToChild = json.getJSONArray(TermType.AND.name());
		}
		else if (json.has(TermType.OR.name()))
		{
			t = new WhenOrTerm();
			jsonToPassToChild = json.getJSONArray(TermType.OR.name());
		}
		else if (json.has(TermType.THEN.name()))
		{
			t = new WhenThenTerm(duration);
			jsonToPassToChild = json.getJSONArray(TermType.THEN.name());
		}
		else 
		{
			t = new WhenEventTerm(json);
			startListening = false;
		}
		t.setLivepolicy(livepolicy);
		t.registerListener(parentListener);
		
		if (jsonToPassToChild!=null) 
		{
			t.children = WhenTerm.createList(jsonToPassToChild, t, startListening); 
		}
		
		t.reset();
		
		if (startListening) t.startListening();
		
		
		return t;
	}
	
	public static List<WhenTerm> createList(JSONArray json, WhenTerm parentTerm, boolean startListening) throws JSONException
	{
		if (parentTerm.getTermType() != TermType.EVENT)
		{
			List<WhenTerm> children = new ArrayList<WhenTerm>();
			
			for (int index = 0; index < json.length(); index++)
			{
				children.add(WhenTerm.create(parentTerm.livepolicy, json.getJSONObject(index), (TermListener)parentTerm, startListening));
			}
			
			return children;
		}
		return null;
	}
	
	public abstract void reset();
	public abstract boolean evaluate();
	public abstract TermType getTermType();
	
	public void registerListener(TermListener parentListener)
	{
		this.parentListener = parentListener;
	}
	
	public void startListening() 
	{
		if (children!=null)
		{
			for (WhenTerm child : children)
			{
				child.startListening();
			}
		}
	}

	public void stopListening() {
		if (children!=null)
		{
			for (WhenTerm child : children)
			{
				child.stopListening();
			}
		}
	}
	
	public boolean evaluateEventConditions(WhenTerm forWhenTerm) {
		if (forWhenTerm.getTermType() == TermType.EVENT) 
		{
			return ((WhenEventTerm) this).evaluateCondition();
		}
		
		for (WhenTerm c : forWhenTerm.getChildren())
		{
			if (evaluateEventConditions(c))
			{
				return true;
			}
		}
		return false;
	}
	
	public void setLivepolicy(LivePolicy livepolicy) {
		this.livepolicy = livepolicy;
	}

	public List<WhenTerm> getChildren()
	{
		return children;
	}

	
}
