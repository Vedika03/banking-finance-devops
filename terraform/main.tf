provider "aws" {
  region = "us-east-1"
}

# Security group for RDS
resource "aws_security_group" "rds_sg" {
  name        = "rds-mysql-sg"
  description = "Allow MySQL access"
  vpc_id      = "vpc-057294980a923d2c6"

  ingress {
    description = "Allow MySQL from EC2 SG"
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    security_groups = ["sg-02817119a277bbd53"] # <-- link EC2 SG
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# RDS MySQL instance
resource "aws_db_instance" "bankingdb" {
  identifier              = "bankingdb"
  allocated_storage       = 20
  engine                  = "mysql"
  engine_version          = "8.0"
  instance_class          = "db.t3.micro"
  username                = "admin"
  password                = "Admin12345!"   # ⚠️ for demo, use SSM in real projects
  db_subnet_group_name    = "default"
  vpc_security_group_ids  = [aws_security_group.rds_sg.id]
  publicly_accessible     = true
  skip_final_snapshot     = true
}
