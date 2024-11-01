SELECT s.*
FROM Selo s
JOIN SeloUsuario su ON s.id_selo = su.id_selo
WHERE su.id_usuario = :id_usuario;  -- Substitua :id_usuario pelo ID desejado

SELECT e.*
FROM Estabelecimento e
WHERE (
    6371 * acos(
        cos(radians(:latitude)) * cos(radians(e.latitude)) * 
        cos(radians(e.longitude) - radians(:longitude)) + 
        sin(radians(:latitude)) * sin(radians(e.latitude))
    )
) <= 1;  -- 1 km

SELECT e.*
FROM Estabelecimento e
JOIN Avaliacao a ON e.id_estabelecimento = a.id_estabelecimento
JOIN CategoriaAceTipoEstab cat ON e.id_tipo_estabeleci = cat.id_tipo_estabeleci
WHERE (a.nota = :nota OR :nota IS NULL)
AND (cat.categoria_ac_id IN (:lista_categoria) OR :lista_categoria IS NULL)
AND (e.id_tipo_estabeleci = :id_tipo_estabeleci OR :id_tipo_estabeleci IS NULL);

SELECT o.*
FROM Ocorrencia AS o
WHERE (
    6371 * acos(
        cos(radians(:latitude)) * cos(radians(o.latitude)) *
        cos(radians(o.longitude) - radians(:longitude)) +
        sin(radians(:latitude)) * sin(radians(o.latitude))
    )
) <= 1;  -- 1 km

SELECT o.*, COUNT(v.id_usuario) AS total_votos
FROM Ocorrencia AS o
LEFT JOIN Votos AS v ON o.id_ocorrencia = v.id_ocorrencia
WHERE o.id_ocorrencia = :id_ocorrencia  -- Substitua pelo ID desejado
GROUP BY o.id_ocorrencia;

SELECT ca.*
FROM CategoriaAcessibilidade AS ca
JOIN UsuarioCategoria AS uc ON ca.categoria_ac_id = uc.categoria_ac_id
WHERE uc.id_usuario = :id_usuario;  -- Substitua pelo ID desejado

SELECT a.*
FROM Avaliacao AS a
WHERE a.id_estabelecimento = :id_estabelecimento;  -- Substitua pelo ID desejado

SELECT a.*
FROM Avaliacao AS a
WHERE a.id_usuario = :id_usuario;  -- Substitua pelo ID desejado

