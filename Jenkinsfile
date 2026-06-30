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
                sh 'npm install'
            }
        }
        stage('Test') {
            steps {
                sh 'npm test'
            }
        }
    }

    post {
        success {
            echo "Pipeline réussi — build #${env.BUILD_NUMBER}"
        }
        failure {
            echo 'Le pipeline a échoué — voir la console pour le détail'
        }
    }
}
