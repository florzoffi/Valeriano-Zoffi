module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City
import Link 
import Quality 
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunnels) new_city
  |  new_city `elem` cities = error "La ciudad que está tratando de agregar a la región ya existe en esta."
  |  otherwise = Reg (new_city : cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) city1 city2 quality
  |  linkedR (Reg cities links tunnels) city1 city2 = error "Estas ciudades ya tienen un link establecido"
  |  otherwise = Reg cities (new_link : links) tunnels
  where 
    new_link = newL city1 city2 quality

tunelR :: Region -> [City] -> Region
tunelR (Reg cities links tunnels) cities_list
  | length cities_list < 2 = error "Debe proporcionar al menos dos ciudades para crear un túnel."
  | otherwise = if all (hasCapacityForTunnel (Reg cities links tunnels)) (pairwise cities_list)
                   then Reg cities links (new_tunnel : tunnels)
                   else error "No hay suficiente capacidad en algún enlace para crear el túnel."
  where
    pairwise [] = []
    pairwise [a] = []
    pairwise (x:y:xs) = (x, y) : pairwise (y:xs)

    new_tunnel = newT (findcitieslinks links cities_list)

hasCapacityForTunnel :: Region -> (City, City) -> Bool
hasCapacityForTunnel region (city1, city2) =
  case findL city1 city2 links of
    link -> availableCapacityForR region city1 city2 > 0
  where
    (Reg _ links _) = region

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) city1 city2 = any (connectsT city1 city2) tunnels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = any (linksL city1 city2) links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg _ _ tunnels) city1 city2 = delayT (head $ counTunnels city1 city2 tunnels)

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg _ links tunnels) city1 city2 
  |  any (usesT link) tunnels = capacity - length (counTunnels city1 city2 tunnels)
  |  otherwise = capacity
  where
    link = findL city1 city2 links
    capacity = capacityL link

findcitieslinks :: [ Link ] -> [ City ] -> [ Link ]
findcitieslinks links cities 
  |  length cities == 2 = [findL (head cities) (cities !! 1) links]
  |  otherwise = findL (head cities) (cities !! 1) links : findcitieslinks links (tail cities)

findL :: City -> City -> [Link] -> Link
findL city1 city2 links
  | any (linksL city1 city2) links = head $ filter (linksL city1 city2) links
  | otherwise = error "No están conectados"

counTunnels :: City -> City -> [Tunel] -> [Tunel]
counTunnels city1 city2 xs = [x | x <- xs, connectsT city1 city2 x]