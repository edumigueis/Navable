import 'package:latlong2/latlong.dart';

class Warning {
  final int idOcorrencia;
  final int idUsuario;
  final int idTipoOcorrencia;
  final WarningType tipoOcorrencia;
  final LatLng location;

  Warning({
    required this.idOcorrencia,
    required this.idUsuario,
    required this.idTipoOcorrencia,
    required this.tipoOcorrencia,
    required this.location,
  });

  factory Warning.fromJson(Map<String, dynamic> json) {
    return Warning(
      idOcorrencia: json['idOcorrencia'] as int,
      idUsuario: json['idUsuario'] as int,
      idTipoOcorrencia: json['idTipoOcorrencia'] as int,
      tipoOcorrencia: WarningType.fromJson(json['tipoOcorrencia']),
      location: LatLng(
        (json['latitude'] as num).toDouble(),
        (json['longitude'] as num).toDouble(),
      ),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idOcorrencia': idOcorrencia,
      'idUsuario': idUsuario,
      'idTipoOcorrencia': idTipoOcorrencia,
      'tipoOcorrencia': tipoOcorrencia.toJson(),
      'latitude': location.latitude,
      'longitude': location.longitude,
    };
  }
}

class WarningType {
  final int idTipoOcorrencia;
  final String nome;
  final String icone;

  WarningType({
    required this.idTipoOcorrencia,
    required this.nome,
    required this.icone,
  });

  factory WarningType.fromJson(Map<String, dynamic> json) {
    return WarningType(
      idTipoOcorrencia: json['idTipoOcorrencia'] as int,
      nome: json['nome'] as String,
      icone: json['icone'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idTipoOcorrencia': idTipoOcorrencia,
      'nome': nome,
      'icone': icone,
    };
  }
}
