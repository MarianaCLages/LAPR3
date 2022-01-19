#include <stdio.h>
#include <stdbool.h>
#include "calculate_energy_consumption.h"

//#define each(item, array, length) \
//(typeof(*(array)) *p = (array), (item) = *p; p < &((array)[length]); p++, (item) = *p) //foreach macro

bool calculate_energy_consumption(Container* container_array, float generatorCapacity, int numContainers){

    float totalEnergyConsumption = 0;
    float energyConsumption;

    if(numContainers == 0) {
        return true;
    }
    
    //for each(item, container_array, numContainers) {
    //    energyConsumption = energy_needed(container_array, item.xPos, item.yPos, item.zPos);
    //    totalEnergyConsumption += energyConsumption;
    //}

    if(generatorCapacity >= totalEnergyConsumption){
        return true;
    }

    return false;
    
}
