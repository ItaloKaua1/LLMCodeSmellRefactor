public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    // Constructor to initialize mandatory fields
    public Reference(String title, String link, String language) {
        if (title == null || title.isEmpty() || link == null || link.isEmpty() || language == null || language.isEmpty()) {
            throw new IllegalArgumentException("Title, link, and language cannot be null or empty.");
        }
        this.title = title;
        this.link = link;
        this.language = language;
    }

    // Getters and setters with encapsulated logic where applicable

    public String getTitle() {
        return title;
    }

    public void updateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void updateAccessRights(String accessRights) {
        if (!isValidAccessRights(accessRights)) {
            throw new IllegalArgumentException("Invalid access rights value.");
        }
        this.accessRights = accessRights;
    }

    public String getLicense() {
        return license;
    }

    public void updateLicense(String license) {
        this.license = license;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void toggleDownloadable() {
        this.isDownloadable = !this.isDownloadable;
    }

    public int getRating() {
        return rating;
    }

    public void rate(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void incrementDownloadCount() {
        if (isDownloadable) {
            this.downloadCount++;
        } else {
            throw new IllegalStateException("Resource is not downloadable.");
        }
    }

    public int getShareCount() {
        return shareCount;
    }

    public void incrementShareCount() {
        this.shareCount++;
    }

    // Example of encapsulated logic
    private boolean isValidAccessRights(String accessRights) {
        return accessRights != null && (accessRights.equals("Public") || accessRights.equals("Private") || accessRights.equals("Restricted"));
    }

    // High-level behavior
    public String summarize() {
        return String.format("Title: %s, Language: %s, Views: %d, Downloads: %d", title, language, viewCount, downloadCount);
    }

    @Override
    public String toString() {
        return String.format("Reference[Title: %s, Link: %s, Language: %s, Rating: %d]", title, link, language, rating);
    }
}