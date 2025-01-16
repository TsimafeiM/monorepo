CREATE TABLE Chevaliers (
	id_chevalier INT PRIMARY KEY NOT NULL,
	nom VARCHAR(50),
	force INT,
	sagesse INT,
	courage INT
);
CREATE TABLE Quetes (
	id_quete INT PRIMARY KEY NOT NULL,
	id_chevalier INT,
	CONSTRAINT fk_chevaliers FOREIGN KEY (id_chevalier) REFERENCES Chevaliers(id_chevalier),
	date_debut DATE,
	date_fin DATE,
	reussite BOOLEAN
);
CREATE TABLE Lieux (
	id_lieu INT PRIMARY KEY NOT NULL,
	nom_lieu VARCHAR(50),
	type_lieu VARCHAR(50),
	danger VARCHAR(50)
);
CREATE TABLE Indices (
	id_indice INT PRIMARY KEY NOT NULL,
	id_quete INT,
	CONSTRAINT fk_quetes FOREIGN KEY (id_quete) REFERENCES Quetes(id_quete),
	description TEXT,
	difficulte VARCHAR(50),
	id_lieu INT,
	CONSTRAINT fk_lieux FOREIGN KEY (id_lieu) REFERENCES Lieux(id_lieu)
);


INSERT INTO Chevaliers (id_chevalier, nom, force, sagesse, courage) VALUES
(1, 'Lancelot', 9, 7, 10),
(2, 'Gauvain', 8, 8, 9),
(3, 'Perceval', 7, 9, 8),
(4, 'Tristan', 10, 6, 7),
(5, 'Galaad', 6, 10, 9),
(6, 'Bohort', 7, 8, 8),
(7, 'Lionel', 8, 6, 9);

INSERT INTO Quetes (id_quete, id_chevalier, date_debut, date_fin, reussite) VALUES
(1, 5, '2024-03-15', '2024-03-20', true),
(2, 2, '2024-04-01', '2024-04-10', true),
(3, 3, '2024-04-15', '2024-04-22', false),
(4, 1, '2024-05-01', '2024-05-15', true),
(5, 4, '2024-05-10', '2024-05-20', false),
(6, 2, '2024-03-22', '2024-03-28', true),
(7, 1, '2024-04-05', '2024-04-18', true),
(8, 6, '2024-04-20', '2024-04-28', false),
(9, 3, '2024-05-05', '2024-05-12', true),
(10, 7, '2024-05-15', '2024-05-25', true);

INSERT INTO Lieux (id_lieu, nom_lieu, type_lieu, danger) VALUES
(1, 'Lac de l''oubli', 'lac', 'faible'),
(2, 'Forêt enchantée', 'forêt', 'moyen'),
(3, 'Donjon du château hanté', 'donjon', 'élevé'),
(4, 'Grotte du dragon rouge', 'grotte', 'très élevé'),
(5, 'Cachette secrète de Merlin', 'cachette', 'légendaire'),
(6, 'Vallée des chevaliers errants', 'vallée', 'moyen'),
(7, 'Tour du sorcier maléfique', 'tour', 'élevé'),
(8, 'Caverne des murmures', 'caverne', 'faible'),
(9, 'Antre du loup blanc', 'antre', 'élevé'),
(10, 'Sanctuaire oublié des druides', 'sanctuaire', 'légendaire'),
(11, 'Cimetière des géants', 'cimetière', 'moyen'),
(12, 'Temple de la sagesse éternelle', 'temple', 'légendaire');

INSERT INTO Indices (id_indice, id_quete, description, difficulte, id_lieu) VALUES
(1, 1, 'Trouver l''épée rouillée dans le lac de l''oubli.', 'facile', 1),
(2, 1, 'Résoudre l''énigme du Sphinx.', 'difficile', 5),
(3, 2, 'Vaincre le dragon rouge.', 'très difficile', 4),
(4, 3, 'Traverser la forêt enchantée sans se perdre.', 'moyen', 2),
(5, 4, 'Déchiffrer le message codé sur le bouclier du chevalier noir.', 'difficile', 12),
(6, 2, 'Trouver la clé cachée dans le donjon du château hanté.', 'moyen', 3),
(7, 5, 'Chercher le symbole sacré dans la vallée des chevaliers errants.', 'facile', 6),
(8, 6, 'Escalader la tour du sorcier maléfique.', 'difficile', 7),
(9, 7, 'Trouver la source magique dans la caverne des murmures.', 'moyen', 8),
(10, 8, 'Survivre dans l''antre du loup blanc.', 'très difficile', 9),
(11, 9, 'Prier au sanctuaire oublié des druides.', 'facile', 10),
(12, 10, 'Déterrer l''amulette sacrée dans le cimetière des géants.', 'moyen', 11),
(13, 3, 'Trouver le calice d''or dans la cachette secrète de Merlin.', 'difficile', 5),
(14, 1, 'Retrouver le parchemin ancien dans le temple de la sagesse éternelle.', 'moyen', 12);




select nom, force, sagesse, courage from chevaliers;
select * from chevaliers where force>8;
select * from chevaliers ORDER BY sagesse DESC;

select * from quetes;