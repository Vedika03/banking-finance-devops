pipeline {
    agent any

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['local', 'qa', 'prod'],
            description: 'Select the deployment environment'
        )
    }

    environment {
        GIT_CREDENTIALS = 'git'
        DOCKER_IMAGE     = 'financeme-banking:latest'
        APP_PORT         = '8081'
        APP_URL          = ''
        TARGET_ENV       = 'local'
    }

    stages {
        stage('Set Environment Variables') {
            steps {
                script {
                    if (params.ENVIRONMENT == "local") {
                        env.APP_URL = "http://localhost:${APP_PORT}"
                        env.TARGET_ENV = "local"
                    } else if (params.ENVIRONMENT == "qa") {
                        env.APP_URL = "http://qa.example.com"
                        env.TARGET_ENV = "qa"
                    } else if (params.ENVIRONMENT == "prod") {
                        env.APP_URL = "http://prod.example.com"
                        env.TARGET_ENV = "prod"
                    }
                    echo "Selected Environment: ${params.ENVIRONMENT}"
                    echo "APP_URL set to: ${env.APP_URL}"
                }
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Vedika03/banking-finance-devops.git',
                    credentialsId: "${GIT_CREDENTIALS}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh "mvn test -Dapp.url=${APP_URL}"
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                sh "ansible-playbook ansible-playbook.yml -e target_env=${TARGET_ENV} -e app_url=${APP_URL}"
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
