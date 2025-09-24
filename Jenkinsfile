pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = 'git'
        DOCKER_IMAGE = 'financeme-banking:latest'
        APP_PORT = '8081'
        APP_URL = "http://localhost:${APP_PORT}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Vedika03/banking-finance-devops.git',
                    credentialsId: "${GIT_CREDENTIALS}"
            }
        }

        stage('Setup Jenkins User for Ansible') {
            steps {
                sh '''
                echo "jenkins ALL=(ALL) NOPASSWD: ALL" | sudo tee /etc/sudoers.d/jenkins-nopasswd
                sudo chmod 440 /etc/sudoers.d/jenkins-nopasswd
                '''
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                sh '''
                ansible-playbook ansible-playbook.yml \
                    -i "localhost," \
                    -c local \
                    -e target_env=local \
                    -e app_url=${APP_URL} \
                    --extra-vars "ansible_become=true ansible_become_method=sudo"
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test -Dtest=SeleniumTest"
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh '''
                if [ $(docker ps -aq -f name=financeme-banking) ]; then
                    docker stop financeme-banking || true
                    docker rm financeme-banking || true
                fi
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d -p ${APP_PORT}:${APP_PORT} --name financeme-banking ${DOCKER_IMAGE}"
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check the logs!"
        }
    }
}
