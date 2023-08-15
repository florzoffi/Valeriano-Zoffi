-- LINK ES SOLO UN POZO QUE CONECTA DOS O MÁS CIUDADES PERO ES NECESARIO UN TUNEL

module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City 
import Quality

data Link = Lin City City Quality deriving (Eq, Show)


newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 _) = city == city1 || city == city2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = connectsL city1 link && connectsL city2 link

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ quality) = delayQ quality

findL :: City -> City -> [Link] -> Link
findL city1 city2 links = head $ filter (linksL city1 city2) links 