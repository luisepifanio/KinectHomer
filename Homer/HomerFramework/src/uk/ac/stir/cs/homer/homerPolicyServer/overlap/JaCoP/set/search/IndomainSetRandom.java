/**
 *  IndomainSetMax.java 
 *  This file is part of JaCoP.
 *
 *  JaCoP is a Java Constraint Programming solver. 
 *	
 *	Copyright (C) 2000-2008 Krzysztof Kuchcinski and Radoslaw Szymanek
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  Notwithstanding any other provision of this License, the copyright
 *  owners of this work supplement the terms of this License with terms
 *  prohibiting misrepresentation of the origin of this work and requiring
 *  that modified versions of this work be marked in reasonable ways as
 *  different from the original version. This supplement of the license
 *  terms is in accordance with Section 7 of GNU Affero General Public
 *  License version 3.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package uk.ac.stir.cs.homer.homerPolicyServer.overlap.JaCoP.set.search;

import java.util.Random;

import uk.ac.stir.cs.homer.homerPolicyServer.overlap.JaCoP.core.IntDomain;
import uk.ac.stir.cs.homer.homerPolicyServer.overlap.JaCoP.search.Indomain;
import uk.ac.stir.cs.homer.homerPolicyServer.overlap.JaCoP.set.core.SetVar;


/**
 * IndomainMin - implements enumeration method based on the selection of the
 * maximal value in the domain of variable
 * 
 * @author Krzysztof Kuchcinski and Radoslaw Szymanek
 * @version 3.1
 * @param <T> type of variable being used in search.
 */

public class IndomainSetRandom<T extends SetVar> implements Indomain<T> {

	/**
	 * It creates random indomain heuristic with randomly generated seed for 
	 * random generated.
	 */
	public IndomainSetRandom() {
		seed = new Random();
	}

	/**
	 * It creates random indomain heuristic according to the provided random 
	 * generator.
	 * @param seed
	 */
	public IndomainSetRandom(Random seed) {
		this.seed = seed;
	}

	Random seed;
	
	public int indomain(T var) {
	
		IntDomain difference = var.domain.lub().subtract(var.domain.glb());
		
		int no = seed.nextInt(difference.getSize());
		
		return difference.getElementAt(no);
		
	}

}
