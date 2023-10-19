module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conecta estas dos ciudades distintas
connectsT city1 city2 (Tun links) =  (connectsL city1 (head links) || connectsL city1 (last links)) && (connectsL city2 (head links) || connectsL city2 (last links))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel 
delayT (Tun links) = sum (map delayL links)