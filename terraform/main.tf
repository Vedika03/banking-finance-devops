provider "aws" {
  region = "us-east-1"
}

# VPC
resource "aws_vpc" "banking_vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true

  tags = {
    Name = "banking-vpc"
  }
}

# Subnet 1
resource "aws_subnet" "subnet1" {
  vpc_id                  = aws_vpc.banking_vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "banking-subnet-1"
  }
}

# Subnet 2
resource "aws_subnet" "subnet2" {
  vpc_id                  = aws_vpc.banking_vpc.id
  cidr_block              = "10.0.2.0/24"
  availability_zone       = "us-east-1b"
  map_public_ip_on_launch = true

  tags = {
    Name = "banking-subnet-2"
  }
}

# DB Subnet Group
resource "aws_db_subnet_group" "bankingdb_subnet_group" {
  name       = "bankingdb-subnet-group"
  subnet_ids = [aws_subnet.subnet1.id, aws_subnet.subnet2.id]

  tags = {
    Name = "bankingdb-subnet-group"
  }
}

# Security Group for RDS
resource "aws_security_group" "rds_sg" {
  vpc_id = aws_vpc.banking_vpc.id
  name   = "rds-sg"

  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # ⚠️ Open to world (for testing) – tighten later!
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# RDS Instance
resource "aws_db_instance" "bankingdb" {
  allocated_storage    = 20
  engine               = "mysql"
  engine_version       = "8.0"
  instance_class       = "db.t3.micro"
  db_name              = "bankingdb"
  username             = "admin"
  password             = "YourSecurePassword123!"
  parameter_group_name = "default.mysql8.0"
  skip_final_snapshot  = true

  db_subnet_group_name   = aws_db_subnet_group.bankingdb_subnet_group.name
  vpc_security_group_ids = [aws_security_group.rds_sg.id]
}

# Output RDS Endpoint
output "rds_endpoint" {
  value = aws_db_instance.bankingdb.endpoint
}
