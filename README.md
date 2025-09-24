FinanceMe – DevOps Project (End-to-End CI/CD)
📌 Project Overview
FinanceMe is a Spring Boot–based microservice that simulates a Banking Insurance Service. The project demonstrates a complete DevOps lifecycle, from application development to automated deployment, testing, and monitoring.
The implementation covers: - Microservice development - Containerization - CI/CD pipeline with Jenkins - Automated provisioning & configuration (Terraform + Ansible) - Automated testing (JUnit + Selenium) - Monitoring & visualization (Prometheus + Grafana)
________________________________________
🏗️ Architecture
graph TD
    A[Developer Pushes Code to GitHub] --> B[Jenkins Pipeline]
    B --> C[Maven Build & Unit Tests]
    C --> D[Docker Build & Push]
    D --> E[Ansible Playbook Deployment on EC2]
    E --> F[Spring Boot App Running in Container]
    F --> G[Selenium Automated UI/API Tests]
    F --> H[Prometheus Metrics Collection]
    H --> I[Grafana Dashboards & Alerts]
________________________________________
⚙️ Tech Stack
•	Application: Java 11, Spring Boot 2.7.4, Maven
•	Version Control: GitHub
•	CI/CD: Jenkins
•	Containerization: Docker
•	Provisioning: Terraform
•	Configuration Management: Ansible
•	Testing: JUnit, TestNG, Selenium
•	Monitoring: Prometheus, Grafana
•	Cloud: AWS EC2
________________________________________
✅ Steps Implemented
1. Spring Boot Microservice
•	Implemented Account entity and AccountService
•	AccountController with CRUD APIs: create, update, view, delete
2. Database Integration
•	Configured application.properties for MySQL/RDS
•	H2 fallback considered for local testing
3. Unit Testing
•	TestNG tests written for service layer (TestAccountService.java)
•	mvn clean test executed successfully, reports generated
4. Docker
•	Dockerfile created
•	Docker image built (financeme-banking:latest)
•	Container deployed successfully
5. API Testing
•	All 4 APIs tested using curl
6. Jenkins – CI/CD Pipeline
•	Jenkins installed & configured with Maven and Git
•	Pipeline stages:
1.	Checkout from GitHub
2.	Maven build & test
3.	Build Docker image
4.	Stop old container (if exists)
5.	Deploy new container
•	Successful execution end-to-end
7. Ansible – Configuration Management
•	Playbook created to:
o	Install Docker on EC2
o	Remove old container
o	Deploy new container with financeme-banking:latest
8. Terraform – Infrastructure as Code
•	Terraform used to provision AWS EC2 instances
•	Infra managed with terraform apply & terraform destroy
9. Selenium – Automated Testing
•	Selenium tests created using ChromeDriver (headless)
•	Navigates to running app, validates title, captures screenshot
•	JUnit lifecycle (@BeforeAll, @Test, @AfterAll) implemented
10. Prometheus – Monitoring
•	Installed & configured Prometheus
•	Metrics collected from Spring Boot app
•	Fixed YAML & systemd service configuration
11. Grafana – Visualization
•	Installed Grafana and connected Prometheus datasource
•	Created custom dashboards with multiple panels (CPU, Memory, API metrics)
12. End-to-End Flow
•	Push code → Jenkins pipeline runs → Terraform creates infra → Ansible deploys → Docker app runs → Selenium validates → Prometheus scrapes metrics → Grafana visualizes
•	Complete DevOps cycle achieved with one-click pipeline execution

________________________________________
🚀 How to Run
1.	Clone the repository:
 	git clone https://github.com/Vedika03/banking-finance-devops.git
cd banking-finance-devops
2.	Build the application:
 	mvn clean package
3.	Build and run Docker image:
 	docker build -t financeme-banking:latest .
docker run -d -p 8081:8081 financeme-banking:latest
4.	Access API:
 	curl http://localhost:8081/accounts/view/ACC1001
5.	Run CI/CD via Jenkins pipeline.
6.	Deploy with Ansible.
7.	Validate with Selenium.
8.	Monitor using Prometheus (port 9090) and Grafana (port 3000).
________________________________________
🏁 Conclusion
This project demonstrates the complete DevOps lifecycle with an integrated toolchain. The system supports automated builds, deployments, tests, monitoring, and infra provisioning, all with one-click execution in Jenkins.
