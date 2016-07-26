package ru.sidvi.graylog;

import ru.sidvi.graylog.api.IssueDTO;
import ru.sidvi.graylog.api.RedmineClient;
import ru.sidvi.graylog.api.RedmineClientFactory;
import ru.sidvi.graylog.hash.IssueMarker;

import javax.inject.Inject;
import java.util.List;

public class Redmine {

    private IssueMarker marker;
    private RedmineClientFactory factory;

    @Inject
    public Redmine(IssueMarker marker, RedmineClientFactory factory) {
        this.marker = marker;
        this.factory = factory;
    }

    public void saveIfNonExists(IssueDTO issue, String serverUrl, String apiKey) {
        RedmineClient client= factory.create(serverUrl, apiKey);

        String projectIdentifier = issue.getProjectIdentifier();
        List<IssueDTO> projectIssues = client.getAll(projectIdentifier);

        if (isExists(issue, projectIssues)) {
            client.create(marker.append(issue));
        }
    }

    private boolean isExists(IssueDTO issue, List<IssueDTO> issues) {
        String currentMarker = marker.calculate(issue);
        for (IssueDTO i : issues) {
            String remoteMarker = marker.extract(i);
            if (currentMarker.equals(remoteMarker)) {
                return true;
            }
        }
        return false;
    }
}