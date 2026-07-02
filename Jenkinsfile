pipeline {
    agent {
        docker {
            image 'node:20'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Code récupéré avec succès'
            }
        }
        stage('Build') {
            steps {
                sh 'npm install'
            }
        }
        stage('Test') {
            steps {
                sh 'npm test'
            }
        }
        stage('Utilisation du secret') {
            steps {
                withCredentials([string(credentialsId: 'api-token-demo', variable: 'MON_TOKEN')]) {
                    sh '''
                        echo "Longueur du token : ${#MON_TOKEN}"
                        echo "Token masqué dans les logs : ****"
                        echo "Simulation d'appel API sécurisé..."
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline réussi — build #${env.BUILD_NUMBER}"
        }
        failure {
            echo 'Le pipeline a échoué — voir la console'
        }
    }
}
