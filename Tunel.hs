-- LO QUE VERDADERAMENTE CONECTA LAS CIUDADES, PUEDE HABER TANTOS TUNELES COMO LA CAPACITY DE LOS LINKS LO PERMITA

module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import City
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conecta estas dos ciudades distintas
connectsT city1 city2 (Tun links) = any (linksL city1 city2) links

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel 
delayT (Tun links) = sum (map delayL links)