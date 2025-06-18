// review.dart
import 'package:intl/intl.dart';

class Review {
  int? idAvaliacao;
  final int idUsuario;
  final int idEstabelecimento;
  final String avaliacao;
  final int nota;
  final DateTime timestamp;

  Review({
    this.idAvaliacao,
    required this.idUsuario,
    required this.idEstabelecimento,
    required this.avaliacao,
    required this.nota,
    DateTime? timestamp,
  }) : this.timestamp = timestamp ?? DateTime.now();

  factory Review.fromJson(Map<String, dynamic> json) {
    return Review(
      idAvaliacao: json['idAvaliacao'],
      idUsuario: json['idUsuario'],
      idEstabelecimento: json['idEstabelecimento'],
      avaliacao: json['avaliacao'],
      nota: json['nota'],
      timestamp: json['timestamp'] != null
          ? DateFormat('yyyy-MM-dd').parse(json['timestamp'])
          : DateTime.now(),
    );
  }

  Map<String, dynamic> toJson() {
    final formatter = DateFormat('yyyy-MM-dd');
    return {
      'idAvaliacao': idAvaliacao,
      'idUsuario': idUsuario,
      'idEstabelecimento': idEstabelecimento,
      'avaliacao': avaliacao,
      'nota': nota,
      'timestamp': formatter.format(timestamp),
    };
  }
}