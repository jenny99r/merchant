package com.insanejamferry.unit.helpers;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

public class TestUriInfo implements UriInfo {
    private String path;

    public TestUriInfo(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getPath(boolean decode) {
        return null;
    }

    @Override
    public List<PathSegment> getPathSegments() {
        return null;
    }

    @Override
    public List<PathSegment> getPathSegments(boolean decode) {
        return null;
    }

    @Override
    public URI getRequestUri() {
        return null;
    }

    @Override
    public UriBuilder getRequestUriBuilder() {
        return null;
    }

    @Override
    public URI getAbsolutePath() {
        return null;
    }

    @Override
    public UriBuilder getAbsolutePathBuilder() {
        return UriBuilder.fromPath(path);
    }

    @Override
    public URI getBaseUri() {
        return null;
    }

    @Override
    public UriBuilder getBaseUriBuilder() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getPathParameters() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getPathParameters(boolean decode) {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParameters() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParameters(boolean decode) {
        return null;
    }

    @Override
    public List<String> getMatchedURIs() {
        return null;
    }

    @Override
    public List<String> getMatchedURIs(boolean decode) {
        return null;
    }

    @Override
    public List<Object> getMatchedResources() {
        return null;
    }

    @Override
    public URI resolve(URI uri) {
        return null;
    }

    @Override
    public URI relativize(URI uri) {
        return null;
    }
}
