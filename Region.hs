module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

import City
import Link 
import Quality 
import Tunel 

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR cities links tunnels = Reg [cities] [links] [tunnels]

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunnels) new_city = Reg (new_city:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg _ links _) city1 city2 quality = Reg _ (new_link:links) _ 
  where 
    new_link = newL city1 city2 quality

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región !!!
tunelR (Reg _ links tunnels) [city1, city2]
  | findL city1 city2 links = Reg _ _ (newT links : tunnels)
  | otherwise = error "Usted debe crear un link primero"

pathR :: Region -> Region -> 

linksForR :: Region -> Region -> Region


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) city1 city2 = any (connectsT city1 city2) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = any (linksL city1 city2) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg _ links _) city1 city2 = delayL (findL city1 city2 links)

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg _ links _) city1 city2 = capacityL (findL city1 city2 links)

usedCapacityForR :: 