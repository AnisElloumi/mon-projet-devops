pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Code récupéré avec succès'
            }
        }
        stage('Build') {
            steps {
                echo 'Étape de build'
            }
        }
        stage('Test') {
            steps {
                echo 'Étape de test'
            }
        }
    }

    post {
        success {
            echo "Pipeline réussi — build #${env.BUILD_NUMBER}"
        }
        failure {
            echo 'Le pipeline a échoué'
        }
    }
}
