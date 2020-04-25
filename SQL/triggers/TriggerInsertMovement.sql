CREATE TRIGGER insertMovements
  BEFORE INSERT
  ON movement
  FOR EACH ROW
  EXECUTE PROCEDURE public."normalStock"();