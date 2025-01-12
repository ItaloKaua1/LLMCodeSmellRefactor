package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality){
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    public class ReferenceDetails {
        private Metadata metadata;
        private AccessControl accessControl;
        private Statistics statistics;
        private boolean downloadable;

        public ReferenceDetails(Metadata metadata, AccessControl accessControl, Statistics statistics, boolean downloadable) {
            this.metadata = metadata;
            this.accessControl = accessControl;
            this.statistics = statistics;
            this.downloadable = downloadable;
        }

        // Métodos para manipulação dos dados, em vez de apenas armazená-los

        public boolean canBeAccessedBy(String userAccessRights) {
            // A lógica de controle de acesso pode ser movida aqui
            return accessControl.hasAccess(userAccessRights);
        }

        public void incrementViewCount() {
            // A lógica de incrementação de visualizações pode ser encapsulada aqui
            statistics.incrementViews();
        }

        public void incrementShareCount() {
            // A lógica de incrementação de compartilhamentos pode ser encapsulada aqui
            statistics.incrementShares();
        }

        public boolean isDownloadable() {
            return downloadable;
        }

        public String getShortDescription(int maxLength) {
            // Lógica de descrição curta pode ser encapsulada aqui
            return metadata.getShortDescription(maxLength);
        }

        public boolean allowsSharing() {
            return metadata.allowsSharing();
        }

        // Validations or complex transformations can also be added here if necessary

        // Getters
        public Metadata getMetadata() {
            return metadata;
        }

        public AccessControl getAccessControl() {
            return accessControl;
        }

        public Statistics getStatistics() {
            return statistics;
        }

        public boolean isDownloadableStatus() {
            return downloadable;
        }
    }


    public class Reference {
        private Metadata metadata;
        private AccessControl accessControl;
        private Statistics statistics;
        private boolean downloadable;

        public Reference(ReferenceDetails details) {
            this.metadata = details.getMetadata();
            this.accessControl = details.getAccessControl();
            this.statistics = details.getStatistics();
            this.downloadable = details.isDownloadable();
        }

        public boolean canBeAccessedBy(String userAccessRights) {
            return accessControl.hasAccess(userAccessRights);
        }

        public void incrementViewCount() {
            statistics.incrementViews();
        }

        public void incrementShareCount() {
            statistics.incrementShares();
        }

        public boolean isDownloadable() {
            return downloadable;
        }

        // Use Metadata behavior
        public String getShortDescription(int maxLength) {
            return metadata.getShortDescription(maxLength);
        }

        public boolean allowsSharing() {
            return metadata.allowsSharing();
        }

        // Getters
        public Metadata getMetadata() {
            return metadata;
        }

        public AccessControl getAccessControl() {
            return accessControl;
        }

        public Statistics getStatistics() {
            return statistics;
        }
    }



    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable){
         this.editAudio(audioQuality, isDownloadable, properties.get(0), properties.get(1), properties.get(2), properties.get(3), properties.get(4), properties.get(5), intProperties.get(0),  intProperties.get(1), intProperties.get(2));
     }

     private void editVideoAttributes(int rating, String language, int viewCount, int shareCount,boolean isDownloadable){
         this.setRating(rating);
         this.setShareCount(shareCount);
         this.setViewCount(viewCount);
         this.setDownloadable(isDownloadable);
         this.setLanguage(language);
     }

     public void editBasic(String title, String description, String link){
         this.setTitle(title);
         this.setDescription(description);
         this.setLink(link);
     }

}
