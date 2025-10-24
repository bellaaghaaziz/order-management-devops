pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'Maven3'
    }

    environment {
        SONARQUBE = 'sonarqube'
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials' // Jenkins credentials ID
        DOCKERHUB_USERNAME = 'bellaghaaziz'
        IMAGE_NAME = 'order-backend'
        IMAGE_TAG = "build-\${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/bellaaghaaziz/order-management-devops.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=order-management \
                        -Dsonar.host.url=http://192.168.56.10:9001 \
                        -Dsonar.login=$SONAR_AUTH_TOKEN
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t \${DOCKERHUB_USERNAME}/\${IMAGE_NAME}:\${IMAGE_TAG} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "\${DOCKERHUB_CREDENTIALS}", usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        sh '''
                            echo "$PASS" | docker login -u "$USER" --password-stdin
                            docker push ${USER}/${IMAGE_NAME}:${IMAGE_TAG}
                            docker logout
                        '''
                    }
                }
            }
        }

        
    }

    post {
        success {
            echo "✅ Build, Sonar analysis, Docker push, and K8s deploy succeeded!"
        }
        failure {
            echo "❌ Pipeline failed. Check logs."
        }
    }
}
