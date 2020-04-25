--cantidad de ingresos ALTAS

	-- X año

select sum(quantity) as "Cantidad" , sum(total) as "Total", '2019' as Periodo
from movement
where date >= '2019/01/01' and date < '2020/01/01' and id_movement_type = 1
group by id_movement_type

	-- X mes

select sum(quantity) as "Cantidad de ingresos" , sum(total) as "Total gastado", 'Febrero - 2019' as Periodo from movement
where extract(month from movement.date) = 2 and extract(year from movement.date) = 2019 and
id_movement_type = 1
group by id_movement_type

--Promedio de ingreso de producto 

select id_product as "Id Producto",
(select brand as "Marca" from product where id = id_product),
(select name as "Nombre" from product where id = id_product),
(select unity as "Especificacion" from product where id = id_product)
,count(id_product) as "Cantidad de Compras", Round(avg(quantity)) as "Promedio de cantidad de compras", '2019 , 2020' as "Periodo"
from movement 
where id_movement_type = 1
GROUP BY id_product
ORDER BY  id_product

 --Promedio de egreso de cada producto

select id_product as "Id Producto",
(select brand as "Marca" from product where id = id_product),
(select name as "Nombre" from product where id = id_product),
(select unity as "Especificacion" from product where id = id_product)
,count(id_product) as "Cantidad de Compras", Round(avg(quantity)) as "Promedio de cantidad de compras", '2019 , 2020' as "Periodo"
from movement 
where id_movement_type = 2
GROUP BY id_product
ORDER BY  id_product

--Query con resultado: Porcentaje que ocupa en el almacen cada prodcuto

select id, (select brand as "Marca" from product p where p.id = p1.id),
sum(quantity) * 100 / (select sum(quantity) from product where quantity > 0)::float as "Porcentaje que ocupa",
(select quantity as "Cantidad de productos" from product p2 where p1.id = p2.id),
(select sum(quantity) as "Stock total" from product)
from product p1 where quantity > 0
group by id
order by "Cantidad de productos"






