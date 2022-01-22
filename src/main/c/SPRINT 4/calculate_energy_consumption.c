#include <stdio.h>
#include <stdbool.h>
#include "calculate_energy_consumption.h"

//A Macro that recreates the "for each" function

#define each(item, array, length) \
(typeof(*(array)) *p = (array), (item) = *p; p < &((array)[length]); p++, (item) = *p) //Foreach macro

int calculate_energy_consumption(Container* container_array, float generatorCapacity, int numContainers){

    float totalEnergyConsumption = 0;
    float energyConsumption;

    if(numContainers == 0) {
        return true;
    }
    
    for each(item, container_array, numContainers) {									//Using the for each macro makes it much easier to see all containers inside our array
        energyConsumption = energy_needed(container_array, item.xPos, item.yPos, item.zPos);
        totalEnergyConsumption += energyConsumption;
    }

    if(generatorCapacity >= totalEnergyConsumption){									//In case the generator capacity produces more energy than all containers consumption, we return 2 to print the respective information in the main 
        return 1;
    }

    return 2;																			//In case all containers energy consumption surpasses the generator capacity
}
