BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Registrar" (
	"serial"	TEXT,
	"nTrab"	TEXT,
	"aTrab"	TEXT,
	"fEntrada"	TEXT,
	"fSalida"	TEXT,
	"cliente"	TEXT,
	FOREIGN KEY("nTrab") REFERENCES "Trabajador"("nombre"),
	FOREIGN KEY("serial") REFERENCES "Producto"("serial")
);
CREATE TABLE IF NOT EXISTS "Producto" (
	"serial"	TEXT,
	"desc"	TEXT,
	"coment"	TEXT,
	"cant"	INTEGER,
	"tipo"	TEXT,
	PRIMARY KEY("serial")
);
CREATE TABLE IF NOT EXISTS "Trabajador" (
	"nombre"	TEXT,
	"apellido"	TEXT,
	PRIMARY KEY("nombre","apellido")
);
COMMIT;
