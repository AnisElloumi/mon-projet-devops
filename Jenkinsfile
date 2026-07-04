pipeline {
    agent any

    environment {
        IMAGE_NAME = 'aniselloumi/mon-projet-devops'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Code récupéré avec succès'
            }
        }

        stage('Tests parallèles') {
            parallel {
                stage('Tests Node.js') {
                    agent { docker { image 'node:20' } }
                    steps {
                        sh 'npm install'
                        sh 'npm test'
                    }
                }
                stage('Tests Maven/Java') {
                    agent { docker { image 'maven:3.9-eclipse-temurin-17' } }
                    steps {
                        dir('java-app') {
                            sh 'mvn clean test'
                        }
                    }
                    post {
                        always {
                            junit 'java-app/target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }

        stage('Build image Docker') {
            steps {
                sh 'docker build -t $IMAGE_NAME:${BUILD_NUMBER} .'
            }
        }

        stage('Push image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push $IMAGE_NAME:${BUILD_NUMBER}
                        docker tag $IMAGE_NAME:${BUILD_NUMBER} $IMAGE_NAME:latest
                        docker push $IMAGE_NAME:latest
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline réussi — build #${env.BUILD_NUMBER}"
        }
        failure {
            echo '❌ Le pipeline a échoué — voir la console'
        }
        always {
            sh 'docker logout'
        }
    }
}
