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

# Internet Gateway
resource "aws_internet_gateway" "banking_igw" {
  vpc_id = aws_vpc.banking_vpc.id

  tags = {
    Name = "banking-igw"
  }
}

# Route Table
resource "aws_route_table" "banking_rt" {
  vpc_id = aws_vpc.banking_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.banking_igw.id
  }

  tags = {
    Name = "banking-rt"
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

# Associate route table with subnets
resource "aws_route_table_association" "subnet1_assoc" {
  subnet_id      = aws_subnet.subnet1.id
  route_table_id = aws_route_table.banking_rt.id
}

resource "aws_route_table_association" "subnet2_assoc" {
  subnet_id      = aws_subnet.subnet2.id
  route_table_id = aws_route_table.banking_rt.id
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
    cidr_blocks = ["0.0.0.0/0"] # ⚠️ Open for testing
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# RDS Instance
resource "aws_db_instance" "bankdb" {
  identifier           = "bankdb-instance"
  allocated_storage    = 20
  engine               = "mysql"
  engine_version       = "8.0"
  instance_class       = "db.t3.micro"
  db_name              = "bankdb"
  username             = "admin"
  password             = "bankdbpass"
  parameter_group_name = "default.mysql8.0"
  skip_final_snapshot  = true
  publicly_accessible  = true
  

  db_subnet_group_name   = aws_db_subnet_group.bankingdb_subnet_group.name
  vpc_security_group_ids = [aws_security_group.rds_sg.id]
}

# Output RDS Endpoint
output "rds_endpoint" {
  value = aws_db_instance.bankdb.endpoint
}
