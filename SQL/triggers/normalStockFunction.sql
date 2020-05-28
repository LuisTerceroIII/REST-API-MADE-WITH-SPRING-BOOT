-- Function: public."normalStock"()

-- DROP FUNCTION public."normalStock"();
---MODIFY

CREATE OR REPLACE FUNCTION public."normalStock"()
  RETURNS trigger AS
$BODY$
declare
productQuantity integer;
product integer;
begin
	select product.id into product from product
	where product.id = new.id_product limit 1;
	raise notice 'Product %', product;

	IF product is NULL THEN
	    raise exception 'El producto involucrado no existe';
	 END IF;

    select product.quantity into productQuantity from product
    where product.id = new.id_product
    limit 1;
    raise notice 'Values of quantity: %', productQuantity;
    raise notice 'Values of new quantity: %', new.quantity;
    raise notice 'Values of new: %', new;

    IF new.id_movement_type = 1 THEN
            raise notice 'dentro de alta';
            productQuantity = productQuantity + new.quantity;
            update product set quantity = productQuantity where id = new.id_product;
    ELSE
        raise notice 'dentro baja';
        IF (productQuantity + new.quantity) < 0 THEN
            raise exception 'No tienes los necesarios productos para efectuar esta operacion, la cantidad de productos qur tienes son:  %', productQuantity;
        ELSE
            productQuantity = productQuantity + new.quantity;
            update product set quantity = productQuantity where id = new.id_product;
        END IF;
	END IF;
    return new;
end;
		$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public."normalStock"()
  OWNER TO postgres;
insert into movement ("id_product","id_movement_type","date","quantity","price_unit","total") values (120,1,'2019/7/20',78,1610,125580);

select * from movement;

