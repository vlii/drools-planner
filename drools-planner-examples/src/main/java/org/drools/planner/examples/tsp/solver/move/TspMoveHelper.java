/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.planner.examples.tsp.solver.move;

import org.drools.WorkingMemory;
import org.drools.planner.examples.tsp.domain.CityAssignment;
import org.drools.runtime.rule.FactHandle;

public class TspMoveHelper {

    public static void moveCityAssignmentAfterCityAssignment(WorkingMemory workingMemory,
            CityAssignment cityAssignment, CityAssignment toNextCityAssignment) {
        FactHandle cityAssignmentFactHandle = workingMemory.getFactHandle(cityAssignment);
        FactHandle toNextCityAssignmentFactHandle = workingMemory.getFactHandle(toNextCityAssignment);

        cityAssignment.setNextCityAssignment(toNextCityAssignment);
        toNextCityAssignment.setPreviousCityAssignment(cityAssignment);

        workingMemory.update(cityAssignmentFactHandle, cityAssignment);
        // Note: for the score rules this isn't currently needed (and a performance leak)
        // but removing it would not be clean code.
        workingMemory.update(toNextCityAssignmentFactHandle, toNextCityAssignment);
    }

    private TspMoveHelper() {
    }

}
