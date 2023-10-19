-- main
import Point
import City
import Quality
import Link
import Tunel 
import Region
import Control.Exception
import System.IO.Unsafe


testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


---------Definicion de variables--------------

-- Flor's City --
pointF = newP 0 0
cityF = newC "Flor's City" pointF
nameF = nameC cityF

-- Isi's City --
pointI = newP 6 9
cityI = newC "Isi's City" pointI
nameI = nameC cityI

-- Belu's City --
pointB = newP 4 2
cityB = newC "Belu's City" pointB
nameB = nameC cityB

-- Pau's City --
pointP = newP 1 3
cityP = newC "Belu's City" pointP
nameP = nameC cityP

-- Qualities --
iron = newQ "Iron" 3 1.0
stone = newQ "Stone" 2 2.0
wood = newQ "Wood" 1 3.0

-- Links --
linkFI = newL cityF cityI iron
linkIB = newL cityI cityB iron
linkPF = newL cityP cityF iron

-- Tuneles --
tunelFB = newT [linkFI, linkIB]
tunelFI = newT [linkFI]

-- Region -- Esta región esta hecha para testear newR, foundR, linkR y tunelR
regionVacia = newR
regionF = foundR regionVacia cityF
regionFI = foundR regionF cityI
regionFIP = foundR regionFI cityP
regionFIPB = foundR regionFIP cityB
regionLinkFIPB = linkR regionFIPB cityI cityP stone
regionLinkTunnelFIPB = tunelR regionLinkFIPB [cityI, cityB, cityP] ---Error no estan conectadas
regionNewLinkTunnelFIPB = linkR regionLinkFIPB cityB cityP stone
regionNewLinkTunnelFIPB2 = linkR regionNewLinkTunnelFIPB cityI cityB stone
regionNewLinkNewTunnelFIPB = tunelR regionNewLinkTunnelFIPB2 [cityI, cityB, cityP]


------------ Testeos -------------

pointTesting :: [Bool] --Testeos modulo point
pointTesting = [pointF == newP 0 0, 
           pointI == newP 6 9, 
           pointB == newP 4 2, 
           difP pointF pointI == 10.816654,
           True]

qualityTesting :: [Bool] --Testeos modulo quality
qualityTesting = [newQ "Iron" 3 1.0 == iron,
          newQ "Stone" 2 2.0 == stone, 
          newQ "Wood" 1 3.0 == wood,
          capacityQ iron == 3,
          delayQ iron == 1.0,
          True]

cityTesting :: [Bool] --Testeos modulo city
cityTesting = [cityF == newC "Flor's City" pointF, nameF == nameC cityF,
              cityI == newC "Isi's City" pointI, nameI == nameC cityI,
              cityB == newC "Belu's City" pointB, nameB == nameC cityB,
              distanceC cityF cityI == 10.816654,
              True]

linkTesting :: [Bool] --Testeos modulo link
linkTesting = [linkFI == newL cityF cityI iron, 
              linkIB == newL cityI cityB iron, 
              capacityL linkFI == 3,
              delayL linkIB == 7.280109889280518,
              connectsL cityF linkFI,
              connectsL cityF linkIB, -- debe dar Falso, la ciudad no pertenece al link
              linksL cityI cityF linkFI,
              linksL cityI cityB linkFI, --debe dar Falso, este link no conecta esta dos ciudades
              True] 

tunelTesting :: [Bool] --Testeos modulo tunel
tunelTesting = [tunelFB == newT [linkFI, linkIB], 
                tunelFI == newT [linkFI],
                connectsT cityF cityB tunelFB,
                connectsT cityI cityP tunelFB, --debe dar Falso, este tunel no conecta esta dos ciudades
                usesT linkFI tunelFB,
                usesT linkIB tunelFI, --debe ser Falso, este tunel no usa este link
                delayT tunelFB == 18.096763715672488,
                True]

regionTesting :: [Bool] -- Testeos modulo region
regionTesting = [delayR regionNewLinkNewTunnelFIPB cityI cityB == 5.221194,
    linkedR regionNewLinkNewTunnelFIPB cityI cityB,
    linkedR regionNewLinkNewTunnelFIPB cityF cityB, --debe ser Falso, estas ciudades no estan conectadas por un link
    connectedR regionNewLinkNewTunnelFIPB cityI cityP,
    connectedR regionNewLinkNewTunnelFIPB cityF cityP, --debe ser Falso, estas ciudades no estan conectadas por un túnel
    availableCapacityForR regionNewLinkNewTunnelFIPB cityI cityP  == 2,
    True]

---Muestra el avance progresivo de la creación de una region
showRegion = [regionVacia, regionF, regionFI, regionFIP, regionFIPB, regionLinkFIPB, regionNewLinkTunnelFIPB, regionNewLinkTunnelFIPB2, regionNewLinkNewTunnelFIPB]
showRegionError = regionLinkTunnelFIPB 